# Running this on a string will return an array of groups
rgx("[0-9]+") -> matcher -> print

match <- "I have 5 cats, drive 3 cars and own 12 houses." -> matcher
match.group(0)
    -> (cats) => "You have ${cats} cats!!??"
    -> print

# Pass 'g' as an argument to rgx to allow
# multiple matches.
# An array of matches will be returned. Each match is
# an array of groups.