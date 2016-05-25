# Declare an array.
arr <- [1, 2, 3, "ok"]

# Length
arr.len() -> print

# Loop it via arr.all()
arr.all(print)

# arr.all() actually functions like 'map' in JavaScript.

[1, 2, 3]
    .all((x) => x + 400)
    .all(cat("..."))
    .all(print)

# Index it!

i <- 0
looper <- fn() {
    "#${i + 1} = ${arr[i]}" -> print
    i <- i + 1
}

looper -> for(arr.len())

