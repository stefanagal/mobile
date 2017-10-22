Smogon Team App

The application is designed to manage Smogon Pokemon teams in order to easily create any kind of team, keep statistics of all your teams and compare them. It will allow you to introduce a pokemon with custom stats and moves, give it a role and add it to any team. For every team, you should be able to track the win-loss percentage against any kind of play style, as well as the componence of the teams. The login is done with a username and an email account and all your progress is stored both locally and in a remote location.

You cannot use the app without being logged in, so at first the app should display a login form (username and password) and a button for sign in. This will open another form requiring a new username, a password (two times) and an email address. After a sign in request, the screen goes back to the login page.

The app itself is structured in three screens. 

The first screen is the pokemon screen. 
It should have an option for adding a pokemon on the screen at all times while displaying all the existing pokemon. The display of a pokemon consists in its name and type. When you click on the add option, a form is displayed requiring a name, type, 4 moves and a role (which can be chosen from stall, pivot, setup, attacker and balanced) and a confirmation button which adds the pokemon to the list and switches to the main sceen when pressed. When you click on a pokemon its stats and the options for delete and edit are displayed. The edit will open the same form as for adding, but with preset values and delete will require another confirmation before deleting the pokemon and returning to the main screen. 

The second screen is the team screen.
It should have an option for adding a team on the screen at all times while displaying all the existent teams. The display of a team consists in its name and predominant type of the pokemon in it. When you click on the add option, the list of pokemon is displayed like in the pokemon screen with checkboxes in front of them. A confirmation button adds the pokemon to the list and switches to the main sceen when pressed if there were 6 pokemon selected and an error will be displayed otherwise. When you click on a team its members and the options for delete, edit and mark are displayed. The edit will open the same form as for adding, but with already checked pokemon and delete will require another confirmation before deleting the team and returning to the main screen. Pressing the mark button will display the actions of marking a generic victory/loss or marking one against a specific type of team. There will also be an undo button for the mark.

The third screen is for statistics.
It will display the first three pokemon as usage, the first three teams as general win percentage, the player's win percentage and the player's team type percentage as a chart. There should be a button for sending an email to the player with these statistics.

The transitions will be done with smooth animations and the display of the options with a flip of the original content.