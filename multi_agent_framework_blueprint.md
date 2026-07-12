# 🛠️ MULTI-AGENT WORKSPACE WORKFLOW BLUEPRINT
**Purpose:** This document is the final architectural blueprint of the Multi-Agent Workflow. It contains the complete folder structure, reusable templates, and setup instructions to replicate this workflow in any future project.

---

## 1. THE ARCHITECTURE MAP

```
                  ┌──────────────────────────────┐
                  │    MAIN MENTOR (Agent 1)     │
                  │   Reads: AGENTS.md           │
                  │   Reads: plan.md, task.md    │
                  │   Teaches the curriculum     │
                  └──────────────┬───────────────┘
                                 │
         ┌───────────────────────┴───────────────────────┐
         ▼                                               ▼
┌──────────────────────────────┐                ┌──────────────────────────────┐
│  DEBUGGER (Agent 3)          │                │   NOTE-KEEPER (Agent 2)      │
│  Triggered by: [ASK]/[DEBUG] │                │   Triggered at: End-of-Day   │
│  Writes to: debug_guide.md   │                │   Reads: transcript, logs    │
│  Archives to: QandA_and_     │                │   Writes to: D: drive notes  │
│    Debug_history/            │                │   Updates: progress_history   │
└──────────────────────────────┘                │   Cleans: QandA_and_Debug_   │
                                                │     history/ folder          │
                                                └──────────────────────────────┘
```

---

## 2. THE DIRECTORY STRUCTURE

```
project-root/
├── .agents/
│   ├── AGENTS.md                       <-- Main Mentor config (role, rules, triggers)
│   ├── plan.md                         <-- 28-day curriculum + note formats
│   ├── debugger_prompt.md              <-- Agent 3 instructions (Sidebar-Q&A)
│   ├── note_keeper_prompt.md           <-- Agent 2 instructions (EOD Note-Keeper)
│   └── QandA_and_Debug_history/
│       └── .gitkeep                    <-- Temp archive for debug logs
├── task.md                             <-- Living checklist of daily sessions
├── progress_history.md                 <-- Project state, bugs, user preferences
└── debug_guide.md                      <-- (Created on-demand by Debugger)
```

---

## 3. SHORTCUT TRIGGERS

| Shortcut | What It Does |
|----------|-------------|
| `[ASK]` | Stops the lesson, invokes the Debugger subagent to answer a question via `debug_guide.md` |
| `[DEBUG]` | Same as `[ASK]` — alternative shortcut for debugging errors |
| `[ASK] <question>` | Sends the question directly to the Debugger for immediate answer |
| `updated debug_guide.md` | Tells the Mentor you edited the file physically; triggers Debugger to read and answer |
| `Resolved` | Archives `debug_guide.md`, Mentor reads the log and resumes the lesson |
| `DONE` / `NEXT` | Moves to the next session or day immediately |

---

## 4. HOW TO SET UP IN A NEW PROJECT

1. Copy the `.agents/` folder into your new project's root folder.
2. Open `AGENTS.md` and customize the role/personality and execution rules for the new topic.
3. Open `plan.md` and replace the curriculum with your new learning plan.
4. Replace the placeholder paths in `AGENTS.md`, `debugger_prompt.md`, and `note_keeper_prompt.md` with your new project's absolute paths.
5. Ensure the `.agents/QandA_and_Debug_history/` folder exists (create a `.gitkeep` inside it).
6. Create `task.md` and `progress_history.md` in the project root.
7. Open your main chat, set workspace to the folder, and type *"I am ready to start."*
