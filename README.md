# Skript

A standalone Skript parser written in Kotlin.

# Limitations

- Parser is easy to push around
  - Parser will match effects that aren't identical (ex. `hey there` matches `hey there bob`)
- Most crucial syntax elements are yet to be implemented