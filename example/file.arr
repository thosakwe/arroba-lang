# File support
file <- "./example/hello_advanced.arr" -> File

# exists <- file.exists()
file.read() -> print

# Also supports create, write, delete

# Directory support
dir = Directory(".")

# Returns array of files and directories
dir.ls()

# Also supports create, exists, delete

# To tell if an entry is a file or directory, just check entry.isDir.

dirs = []
files = []

dir.ls().all(fn(entry) {
    if (entry.isDir) {
        dirs.add(entry)
    } else {
        files.add(entry)
    }
})

print("\nDirectories:")
dirs.all(dir => print("\t${dir.path} (" + dir.ls().len() + " children)"))

print("\nFiles:")
files.all(file => print("\t${file.path}"))