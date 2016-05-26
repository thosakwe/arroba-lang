exports = any()

exports.PI = 22 / 7

exports.abs = fn(x) {
    if (x < 0)
        ret x * -1
    else ret x
}

exports.avg = fn(arr) {
    local:sum = 0

    arr.all(fn(x) {
        sum = sum + x
    })

    ret sum / (arr.len())
}

exports.isPrime = fn(x) {
    if (x <= 1)
        ret false
    else if (x <= 3)
        ret true
    else if ((x % 2 == 0) || (x % 3 == 0))
        ret false

    local:i = 5

    while (i ^ 2 <= x) {
        if (((x % i) == 0) || (x % (i + 2) == 0)) {
            ret false
        }

        i = i + 6
    }

    ret true
}

exports.pow = (x, y) => x ^ y

exports.sum = fn(arr) {
    local:sum = 0

    arr.all(fn(x) {
        sum = sum + x
    })

    ret sum
}

ret exports