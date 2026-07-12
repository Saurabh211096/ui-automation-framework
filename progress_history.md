# 🚀 AUTOMATION BOOTCAMP PROGRESS & CONTEXT FILE
**Target Audience:** The AI Mentor taking over this session.
**Goal:** Read this file alongside the original System Instructions to seamlessly resume the 28-Day Bootcamp.

## 1. CURRENT STATUS (Where to begin)
*   **Completed:** Days 1 through 15 are 100% complete. 
*   **Next Step:** Please start and teach **Day 16 (Playwright Introduction & TypeScript)** from the beginning. 

## 2. USER PREFERENCES & STRICT GUIDELINES (Learned during Days 1-14)
*   **Terminal Environment:** The user utilizes **Git Bash** on Windows (not standard CMD). Provide all terminal commands formatted for Git Bash.
*   **Debugging Dynamic:** If the AI Mentor provides incomplete code or misses a bug, the user will independently debug using other AI tools (like DeepSeek) and report the fix back. The Mentor must acknowledge, validate, and explicitly integrate these fixes into the End-of-Day revision files.
*   **STRICT RULE ENFORCEMENT:** The Mentor must *never* skip the line-by-line code explanations (Rule 1). The Mentor must *never* assume the user knows where a file goes without explicitly stating the package/path (Rule 2). The Mentor must *never* provide partial snippets when updating a class (Rule 3).

## 3. CURRENT PROJECT STATE & ARCHITECTURE
*   **Workspace:** `Desktop/Automation_Bootcamp/ui-automation-framework`
*   **Java Version:** Compiled to Java 17 via `pom.xml`, but the user's local machine runs **Java 23**.
*   **Core Framework Architecture Implemented:**
    *   **Page Object Model (POM):** Pages are in `src/main/java/com/bootcamp/pages`.
    *   **Utilities:** `WaitUtils`, `ConfigReader`, `JsonReader`, `ExcelReader`, `ExtentManager`, `UIInteractionUtils` (for complex mouse/DOM actions), and `DbUtils` (JDBC connection provider) are in `src/main/java/com/bootcamp/utils`.
    *   **Test Base:** `BaseTest.java` (using `ThreadLocal<WebDriver>`) is in `src/test/java/com/bootcamp/base`.
    *   **Listeners:** `ListenerManager.java` is in `src/test/java/com/bootcamp/listeners` (Explicitly moved to the `test` directory to respect Maven boundary scopes).
    *   **Resources:** `config.qa.properties` (with URL, browser, Slack, and database URL/credentials), `log4j2.xml`, `allure.properties`, `testdata.json`, `testdata.xlsx` are in `src/test/resources`.
    *   **Switchboards:** `testng.xml` and `pom.xml` (configured with Jackson, Apache POI, Log4j2, Allure, ExtentReports, and MySQL Connector/J dependency) are at the project root.
    *   **CI/CD Pipeline:** GitHub Actions workflow `.github/workflows/ui-tests.yml` (triggering headless Chrome, mobile emulation, and BrowserStack integrations).
    *   **Slack Webhook Integration:** `SlackNotifier.java` utility configured to send TestNG summary reports to Slack via webhook.

## 4. CRITICAL BUGS FIXED & EDGE CASES HANDLED (Do not overwrite these in future code!)
*   **The Double Parallelism Crash:** On Day 9, running `testng.xml` with `parallel="tests"` alongside a DataProvider with `parallel=true` spawned 15+ browsers, crashing the laptop. Parallelism on DataProviders was removed for local execution to save RAM.
*   **Allure + Java 23 AspectJ Conflict:** The `aspectjweaver` version in `pom.xml` was upgraded to `1.9.24` to prevent Java 23 compatibility crashes.
*   **Allure 3.x vs 2.x Conflict:** The `allure-maven` plugin was explicitly configured with `<reportVersion>2.24.0</reportVersion>` to prevent "0 tests found" errors in the dashboard.
*   **The Screenshot Race Condition:** Standard TestNG `ITestListener` execution misses Allure's recording window. The screenshot logic was successfully moved to the `@AfterMethod` in `BaseTest.java` to ensure Allure captures the `OutputType.BYTES` before the test context closes.
*   **The `mkdirs()` Fix:** Added `destinationPath.getParentFile().mkdirs();` in the Listener to prevent `FileNotFoundException` when saving physical `.png` screenshots.
*   **Mobile Emulation Dimension Bug:** When emulating mobile, `getDriver().manage().window().maximize()` ruins the emulation. The user successfully wrapped it in an `if(!browser.equals("mobile"))` check and applied `.setSize(new Dimension(375, 812))` to properly render the iPhone view.
*   **Docker MySQL Port Conflict:** Exposing container port 3306 on host port 3306 failed with a bind socket Permitted exception due to a local `MySQL80` Windows service running. Stopped the local service or mapped Docker to host port `3307` and updated `db.url` in config properties.
*   **MySQL CLI Quote Hang (`'>`)**: Unclosed quote in insert statements hangs terminal. Escaped by typing `'` then `\c` to clear the line.
*   **Relational Query Order Mismatch:** Row order on multi-table joins is not default-sorted; adding `ORDER BY` is required for precise test assertions.

## 5. REVISION FILES STATUS (Crucial for End-of-Day Generation)
The user maintains multiple revision files. Per Section 4 of the System Instructions, the AI MUST output the updates for these files at the end of every day. Here is the exact state of those files up to Day 15:
1.  **`webui-automation-masterclass.md`**: Contains Concepts #1 through #77. Proceed with Concept #78.
2.  **`webui-practice-prompts.md`**: Up to date. Added database validation prompts.
3.  **`locator-cheat-sheet.md`**: Completed on Day 2.
4.  **`oop-in-automation.md`**: Contains 12 specific OOP applications mapped to the framework (added Abstraction & Interfaces for JDBC).
5.  **`framework-architecture-map.md`**: Up to date. Added `DbUtils.java` and `Day15_DatabaseValidationTest.java`.
6.  **`git-commands-cheatsheet.md`**: Unlocked on Day 13. Up to date.
7.  **`framework-starter-kit.md`**: Contains Templates 1 through 22. Proceed with Template 23.
8.  **`maven-cli-cheatsheet.md`**: Completed on Day 14 (One-stop CLI reference for running tests).
