## GAMING APP REST APIs

Implemented a secure signup and login system using JWT.
Developed the REST APIs that allow users to view, join, and participate in tournaments and leagues.
Provide support for creating, managing, and fetching tournament and league details, including player participation, match results, and prize distributions.

#ENDPOINTS:=

- SignUp or Register: /auth/signUp<br/>
Login: /auth/login<br/>
<br/>
Create Player: /player/create<br/>
Get All Player: /player/getAll<br/>
Get Player by Id: /player/get/{Id}<br/>
Get Players by TeamId: /player/getByTeam/{teamId}<br/>
Update Player: /player/update/{playerId}<br/>
Delete Player: /player/delete/{playerId}<br/>
<br/>
Create Team: /team/create<br/>
Get All Teams: /team/getAll<br/>
Get Team by Id: /team/get/{Id}<br/>
Update Team: /team/update/{id}<br/>
Delete Team: /team/delete/{id}<br/>
<br/>
Create Tournament: /tournament/create<br/>
Get All Tournaments: /tournament/getAll<br/>
Get Tournament by Id: /tournament/get/{Id}<br/>
Update Tournament: /tournamente/update/{id}<br/>
Delete Tournament: /tournament/delete/{id}<br/>
End Tournament: /tournament/endTournament/{tournamentId}<br/>
Get all Teams in a tournament: /tournament/getTeams/{tournamentId}<br/>
Get Ongoing Tournaments: /tournament/getOngoingTournaments<br/>
Get Upcoming Tournaments : /tournament/getUpcomingTournaments<br/>
Add Team to tournament: /tournament/addTeam<br/>
Delete Team from a tournament:/tournament/deleteTeam<br/>
Distribute Prize:/tournament/distributePrize<br/>
<br/>
Create League: /league/create<br/>
Get All Leagues: /league/getAll<br/>
Get League by Id: /league/get/{Id}<br/>
Update League: /league/update/{id}<br/>
Delete League: /league/delete/{id}<br/>
Add Team to League: /league/addTeam<br/>
Delete Team from a League:/league/deleteTeam<br/>
<br/>
Create Match: /match/create<br/>
Get All Matches: /match/getAll<br/>
Get Match by Id: /match/get/{Id}<br/>
Declare Result of a Match: /match/setResult<br/>
