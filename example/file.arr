# File support
file <- "./example/hello_advanced.arr" -> File

# exists <- file.exists()
file.read() -> print

# Also supports create, write, delete

# Directory support
dir = Directory(".")

# Returns array of files and directories
print(dir.ls())

# Also supports create, exists, delete