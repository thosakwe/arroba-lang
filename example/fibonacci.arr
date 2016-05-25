fib = fn (n) {
    if (n <= 1)
        ret n
    ret fib(n - 1) + fib(n - 2)
}

x = 13
print("The ${x}th Fibonacci number is: ${fib(x)}")