package thosakwe.arroba.interpreter.data;

import thosakwe.arroba.interpreter.ArrobaFunction;

import java.io.*;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

public class ArrobaSocket extends ArrobaDatum {
    private String host;
    private int port;
    private Socket socket;
    private ServerSocket serverSocket;

    private ArrobaEvent accept = new ArrobaEvent();

    public ArrobaSocket(String host, int port) {
        this.host = host;
        this.port = port;

        members.put("accept", accept);
        members.put("close", close());
        members.put("connect", connect());
        members.put("listen", listen());
    }

    private ArrobaSocket(Socket accept) {
        this.socket = accept;

        connected();
    }

    private void connected() {
        members.put("close", close());
        members.put("read", read());
        members.put("write", write());
    }

    private ArrobaTask close() {
        return new ArrobaTask() {
            @Override
            public ArrobaFunction yield() {
                return new ArrobaFunction() {
                    @Override
                    public ArrobaDatum invoke(List<ArrobaDatum> args) {
                        try {
                            if (serverSocket != null)
                                serverSocket.close();
                            if (socket != null)
                                socket.close();
                            return ArrobaNumber.True();
                        } catch (Exception exc) {
                            return new ArrobaException(exc);
                        }
                    }
                };
            }
        };
    }

    private ArrobaFunction connect() {
        ArrobaSocket parent = this;
        return new ArrobaFunction() {
            @Override
            public ArrobaDatum invoke(List<ArrobaDatum> args) {
                return new ArrobaTask() {
                    @Override
                    public ArrobaFunction yield() {
                        return new ArrobaFunction() {
                            @Override
                            public ArrobaDatum invoke(List<ArrobaDatum> args) {
                                try {
                                    if (args.isEmpty()) {
                                        socket = new Socket();
                                        socket.connect(new InetSocketAddress(host, port));
                                        parent.connected();
                                        return parent;
                                    }

                                    ArrobaDatum timeout = args.get(0);

                                    if (timeout instanceof ArrobaNumber) {
                                        socket.connect(new InetSocketAddress(host, port), ((ArrobaNumber) timeout).value.intValue());
                                        parent.connected();
                                        return parent;
                                    }

                                    return new ArrobaException("Socket.connect expects argument 1 to be a number.");
                                } catch (Exception exc) {
                                    return new ArrobaException(exc);
                                }
                            }
                        };
                    }
                };
            }

            @Override
            public String toString() {
                return "<Native Function> Socket.connect()";
            }
        };
    }

    private ArrobaFunction listen() {
        ArrobaFunction emit = (ArrobaFunction) accept.members.get("emit");
        return new ArrobaFunction() {
            @Override
            public ArrobaDatum invoke(List<ArrobaDatum> args) {
                try {
                    serverSocket = new ServerSocket();
                    serverSocket.bind(new InetSocketAddress(host, port));

                    return new ArrobaTask() {
                        @Override
                        public ArrobaFunction yield() {
                            return new ArrobaFunction() {
                                @Override
                                public ArrobaDatum invoke(List<ArrobaDatum> args) {
                                    while (true) {
                                        try {
                                            Socket accept = serverSocket.accept();
                                            emit.invoke(new ArrobaSocket(accept));
                                        } catch (Exception exc) {
                                            if (catcher != null)
                                                catcher.invoke(new ArrobaException(exc));
                                        }
                                    }
                                }
                            };
                        }
                    };
                } catch (Exception exc) {
                    return new ArrobaException(exc);
                }
            }

            @Override
            public String toString() {
                return "<Native Function> Socket.listen()";
            }
        };
    }

    private ArrobaFunction read() {
        return new ArrobaFunction() {
            @Override
            public ArrobaDatum invoke(List<ArrobaDatum> args) {
                try {
                    InputStream is = socket.getInputStream();
                    InputStreamReader reader = new InputStreamReader(is);
                    BufferedReader bufferedReader = new BufferedReader(reader);
                    int read = 0;
                    String message = "";
                    String line;

                    while ((line = bufferedReader.readLine()) != null) {
                        if (read > 0)
                            message += "\n";

                        message += line;
                        read++;
                    }

                    List<ArrobaNumber> bytes = new ArrayList<>();
                    for (Byte b : message.getBytes()) {
                        bytes.add(new ArrobaNumber(b.intValue()));
                    }

                    return new ArrobaArray(bytes);
                } catch (Exception exc) {
                    return new ArrobaException(exc);
                }
            }

            @Override
            public String toString() {
                return "<Native Function> Socket.read()";
            }
        };
    }

    private ArrobaFunction write() {
        ArrobaSocket parent = this;

        return new ArrobaFunction() {
            @Override
            public ArrobaDatum invoke(List<ArrobaDatum> args) {
                try {
                    // socket.setTcpNoDelay(true);
                    PrintWriter out = new PrintWriter(socket.getOutputStream(), true);

                    for (ArrobaDatum arg : args) {
                        writeToStream(out, arg);
                    }

                    return parent;
                } catch (Exception exc) {
                    return new ArrobaException(exc);
                }
            }

            @Override
            public String toString() {
                return "<Native Function> Socket.write()";
            }
        };
    }

    private void writeToStream(PrintWriter out, ArrobaDatum datum) {
        if (datum instanceof ArrobaNumber) {
            if (((ArrobaNumber) datum).isByte()) {
                out.print(((ArrobaNumber) datum).value.byteValue());
            } else if (((ArrobaNumber) datum).isInt()) {
                out.print(((ArrobaNumber) datum).value.intValue());
            } else out.print(((ArrobaNumber) datum).value);
        } else if (datum instanceof ArrobaString) {
            out.print(datum.toString());
        } else if (datum instanceof ArrobaArray) {
            Boolean isByteArray = true;

            for (ArrobaDatum item : ((ArrobaArray) datum).items) {
                if (!(item instanceof ArrobaNumber)) {
                    isByteArray = false;
                    break;
                } else if (!((ArrobaNumber) item).isByte()) {
                    isByteArray = false;
                    break;
                }
            }

            if (isByteArray) {
                for (ArrobaDatum item : ((ArrobaArray) datum).items) {
                    ArrobaNumber num = (ArrobaNumber) item;
                    byte b = num.value.byteValue();
                    //out.write(b);
                    out.print(b);
                }
            } else for (ArrobaDatum item : ((ArrobaArray) datum).items) {
                writeToStream(out, item);
            }
        }
    }

    @Override
    public String toString() {
        if (host != null)
            return "<Socket:(" + host + ":" + port + ")>";
        else if (socket != null) {
            InetAddress address = socket.getInetAddress();
            return "<Socket:(" + address.getHostAddress() + ":" + socket.getPort() + ")>";
        } else return "<Socket>";
    }
}
