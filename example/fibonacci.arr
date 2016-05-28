fib = fn (n) {
    if (n <= 1)
        ret n
    else ret (fib(n - 1)) + (fib(n - 2))
}

x = 17
print("The ${x}th Fibonacci number is: ${fib(x)}")