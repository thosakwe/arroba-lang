# Declare an array.
arr <- [1, 2, 3, "ok"]

# Length
arr.len() -> print

# Loop it
arr -> (all((x) => "Array contains: ${x}" -> print))

# Index it!

i <- 0
looper <- fn() {
    "#${i + 1} = ${arr[i]}" -> print
    i <- i + 1
}

looper -> (for(arr.len()))