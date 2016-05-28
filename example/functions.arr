# Full
add1 <- fn(num) {
    ret num + 1
}

# Inline
add2 <- (num) => num + 2

# Call via Arrows
three <- (2 -> add1)
"${three} == 3.0???" -> print

# Call via Parentheses
four <- add2(2)
"${four} == 4.0???" -> print