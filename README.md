# RealEstateFinancing — Debugging with VS Code

Quick notes to run and debug this project in VS Code using Java 21.

1. Ensure Java 21 is installed and `JAVA_HOME` points to it.

2. Reload VS Code after any environment changes so the Java extensions pick up the JDK.

3. Clean the Java language server workspace (if you see odd errors):
   - Press `Ctrl+Shift+P` → `Java: Clean Java Language Server Workspace` → choose `Restart and delete`.

4. Use the provided debug configurations (Run view):
   - `Main` and `Debug Financing` are configured to run in the **Integrated Terminal** so stdin and evaluation work reliably.
   - If you prefer the Debug Console, edit `.vscode/launch.json` and remove or change the `"console": "integratedTerminal"` entry.

5. If you still see `unrecognized request: {_request: evaluate}` in the Debug Console, prefer the integrated terminal (the debug configs already do this) or update the Java extensions (Language Support for Java by Red Hat and Debugger for Java by Microsoft).

6. To compile and run quickly from the terminal:

```powershell
# compile
javac -d out src/main/Main.java src/model/Financing.java src/util/UserInterface.java

# run
java -cp out main.Main
```

If anything still fails in the VS Code debug flow after reloading and cleaning, tell me what you see and I'll iterate further.