# NoAutoJump
[![Downloads](http://cf.way2muchnoise.eu/full_no-auto-jump_downloads.svg)](https://minecraft.curseforge.com/projects/no-auto-jump) [![MCVersion](http://cf.way2muchnoise.eu/versions/no-auto-jump.svg)](https://minecraft.curseforge.com/projects/no-auto-jump)

[![GitHub issues](https://img.shields.io/github/issues/JackyyTV/NoAutoJump.svg)](https://github.com/JackyyTV/NoAutoJump/issues) [![GitHub pull requests](https://img.shields.io/github/issues-pr/JackyyTV/NoAutoJump.svg)](https://github.com/JackyyTV/NoAutoJump/pulls) [![license](https://img.shields.io/github/license/JackyyTV/NoAutoJump.svg)](../dev-1.12.2/LICENSE)

---

## About

This is the GitHub repo for the No Auto Jump Minecraft mod, where the source code and issue tracker are in here.

Submit any bug reports / suggestions via [issue tracker](https://github.com/JackyyTV/NoAutoJump/issues).

[Pull requests](https://github.com/JackyyTV/NoAutoJump/pulls) are welcome if you would like to add features / help with bug fixes or translations.

---

## Contact Me

- Twitter - [@JackyyTV](https://twitter.com/JackyyTV)
- Discord - Jacky#1234
- Twitch - [Jackyy](https://www.twitch.tv/jackyy)
- Reddit - [JackyyTV](https://www.reddit.com/message/compose/?to=JackyyTV)

---

## Setting up workspace / compile the mod yourself

If you would like to set up the workspace yourself to submit PRs of features additions or bug fixes, or compile the mod, here's how you do it.

1. Clone the mod.
    - HTTPS: `git clone https://github.com/JackyyTV/NoAutoJump.git`
    - SSH: `git clone git@github.com:JackyyTV/NoAutoJump.git`
    - Or, use the GitHub desktop app to clone the repo via GUI interface.

2. Setting up the workspace, run: `gradlew eclipse`

3. Either use `gradlew build` to build the jar file (Output is in `build/libs`), or setup the IDE if you are going to modify any codes. Both IntelliJ IDEA and Eclipse are included below since they're more popular IDEs.
    - IntelliJ IDEA: Run `gradlew genIntellijRuns`, then open the `.project` file and import the project into the IDE.
    - Eclipse: Open the `.project` file.
