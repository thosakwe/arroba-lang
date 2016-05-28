JSON = import("<json>")

json = "{\"hello\": \"world\"}"

parsed = JSON.parse(json)
print(parsed)


foo = any()
foo.bar = any()
foo.bar = "baz"
foo.lit = "it's"

stringified = JSON.stringify([foo, foo, foo])
print(stringified)