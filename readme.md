# DFSM Simulator

This program is a Deterministic Finite State Machine (DFSM) simulator.

## Configuration
This program reads information from `configuration.txt` whose is structuring information is below.

Line 1 of `configuration.txt` contains the alphabet of the machine. Each character is separated by a comma with no whitespace. Example: `0,1,2,3,4`.

Line 2 of `configuration.txt` contains the number of states in the DFSM.

Line 3 of `configuration.txt` contains each accepting state of the DFSM. Each state is separated by a comma with no whitespace. Example: `0,2,4`.

Each line after line 3 contains a comma separated list of tuples in the form `(beginState, edge, endState)`. These tuples map states together with the `edge` character.

Formatting of `configuration.txt` is very strict, so a complete `configuration.txt` file should look similar to this:  

```text
0,1
2
0
(0,0,0),(0,1,1)
(1,0,1),(1,1,0)
```

## Runtime
After `configuration.txt` is loaded and the DFSM is configured, enter words into the machine. The word will either be accepted or rejected my the DFSM.

### Author
James Peccia