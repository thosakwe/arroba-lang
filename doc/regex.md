# Regular Expressions

The `rgx` function can return a match, or array of matches.
For multiple matches, pass `"g"` as an argument.

```arroba
// One match
local:match = "420 blaze it" -> rgx("[0-9]+")
print(match[0])

// Array of matches
local:matches = "1337 420" -> rgx("[0-9]+", "g")
```

# Literals

When possible, use a regular expression literal.

**Supported flags:**
* g - *Global/Multiple matches*
* i - *Case insensitive*
* m - *Multiline*
* u - *Unicode*
* c - *Allow comments*

```arroba
// One match
local:match = "420 blaze it" -> /[0-9]+/
print(match[0])

// Array of matches
local:matches = "1337 420" -> /[0-9]+/g
```