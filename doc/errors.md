# Error Handling

Arroba supports try-catch, woohoo!
Error handling also works with [Tasks and `await`](async.md).

```arroba
try {
    // Do something stupid...
} catch(exc) {
    printErr(exc)
}
```