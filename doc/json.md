# JSON

The Arroba interpreter has native support for JSON baked in.
Simply import the `"<json>"` module to access it.

JSON can easily be parsed into a `Datum` or `Array`.

```arroba
JSON = import("<json>")

data = JSON.parse("{\"hello:\":\"world\"}")
print("Hello, ${data.hello}!")
```

And you can serialize any `Datum` into JSON.

```arroba
local:arr = [1, 2, "three"]
local:jsonStr = JSON.stringify(arr)
```