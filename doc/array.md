# Arrays
You know what they are, yo'.

```arroba
local:arr = [1, 2, "three"]

arr.len()
arr[2]

arr.first()
arr.last()

# This is like 'map' in JS
arr.all(fn(x) {
    // ...
})

# 'filter'
arr.where((x) => condition)

arr.reverse()

arr.join(",")
```

# Add/Remove

```arroba

# These are the same
[1, 2, "three"].add(4.0)
[1, 2, "three"] + 4.0

# These are the same
[1, 2, "three"].remove(2)
[1, 2, "three"] - 2
```