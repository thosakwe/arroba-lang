# Piping

The weirdest feature of Arroba.

This is vaguely reticent of `monads`, but more specifically, is a syntax
used to chain functions.

# Example

This will print every word in this string. Notice the
use of transformer functions!

```arroba
"I'm wearing 3 pairs of underpants right now"
    -> /\w+/g
    -> all((match) => match[0])
    -> all(print)
```