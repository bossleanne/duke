   ```
   Hello from
    ____        _        
   |  _ \ _   _| | _____ 
   | | | | | | | |/ / _ \
   | |_| | |_| |   <  __/
   |____/ \__,_|_|\_\___|
   ```
# User Guide
This is a CLI(Command Line Interface) Todolist application written in OOP fashion. By using command line to interactive with the application, it allows you to load, save and amend your todolist.

## Prerequisite
- Able to start a command line shell.
- Java version 11
- JDK 11, update Intellij to the most recent version.

## Using Command Line
- download the `duke.jar` application
- `cd` into the application's path, eg `cd `~/Download/`
- type `java -jar duke.jar`

## Features
- The sections below are shows the feature of this todolist application

#### Feature-Add task to todolist
- Adding tasks to todolist

#### Feature-Remove task to todolist
- Removing any task from the list.

#### Feature-Undo
- Erasing the last change done to the todolist, reverting it to an older state.

#### Feature-Help
- Help is shown when user first loaded the program

#### Feature-View full tasks in todolist
- Displaying a list of all tasks in the todolist application.

#### Feature-Finding all tasks containing any keyword:
- Searching for tasks in the todolist.

#### Feature-Saving the record
- Saving the todolist in the hard disk automatically after user exit command executed.

#### Feature-Reload the record
- Todolist historical tasks will loaded to this application automatically during launching.


## Usage

### `todo` `event` `deadline`
- Adding tasks to todolist

Example of usage:

`todo <task description>`

`deadline <task description> /by <date and time>`

`event <task description> /at <date and time>`

Expected outcome:
```
Got it. I've added this task:
[T][ ] read book
Now you have 1 tasks in the list.
```
```
Got it. I've added this task:
[E][ ] project meeting at 6:00 pm
Now you have 2 tasks in the list.
```
```
Got it. I've added this task:
[D][ ] submission by Nov 02 2021
Now you have 3 tasks in the list.
```
Description of the outcome.
```
`T`or`E`or`D` are represent for the task type, and the box `[ ]` next by represent the status of the task whether is done or still in progress. The text `read book` next by is the things that user want to do. If there's data object, `/by` or `/at` used for to track the time of the task
```
### `done` `delete`
- Update the todolist

Example of usage: 

`done <index from displayed list>`

`delete <index from displayed list>`

Expected outcome:
```
Nice! I've marked this task as done:
[E][X] project meeting (at: 6:00 pm)
```
```
Noted. I've removed this task:
[D][ ] submission (by: Nov 02 2021,11:59 pm)
Now you have 3 tasks in the list..
```
Description of the outcome.
```
`[X]` in the box represent the status of the tasks, "x" means done, " "(space) means in prograss. 
Delete will remove the task from the todolist.
```


### `undo`
- revert to previous step

Example of usage: `undo`

Expected outcome:
```
undo
1.[T][ ] readbook
```
Description of the outcome.
```
Displayed the latest todolist and refresht the change
```

### `find`
- search tasks from the todolist

Example of usage: 
`find <keywords>`

Expected outcome:
```
Here are the matching tasks in your list:
1.[D][ ] submission (by: Nov 02 2021)
2.[D][X] submission (by: 11:59 pm)
```
Description of the outcome.
```
Print out the tasks that mathes the keywords in the list in desired format.
```

### `list`
- list out all tasks from the todolist

Example of usage: 
`list`

Expected outcome:
```
list
1.[T][ ] readbook
```
Description of the outcome.
```
Print out the tasks in the list in desired format.
```

### `help`
- list out todoList usage instructions

Example of usage: 
`help`

Expected outcome:
```Help instructions only```

### `bye`
- exist the program

Example of usage: 
`bye`

Expected outcome:
```
Bye. Hope to see you again soon!
```
