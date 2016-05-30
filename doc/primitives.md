# Primitives
Arroba supports a few types of primitives:

* Number
* String
* Array
* Datum
* Functions
    
**All data in Arroba inherits from `Datum`.**
    
Yes, there is only one type of number in Arroba - a double-precision one. However, for convenience's sake,
if a number is an integer, then its string representation is simply the integer form.

`Data` are similar to objects in JavaScript. They represent mutable blobs of data that you can attach members to.

`Strings` are strings of characters. To declare a raw string, prefix it with 'r'.

Example:

```
local:str = r"This backslash is not escaped \"
```

And yes, functions are first-class objects.