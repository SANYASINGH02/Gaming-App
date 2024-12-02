**GAMING APP REST APIs**

Implemented a secure signup and login system using JWT.
Developed the REST APIs that allow users to view, join, and participate in tournaments and leagues.
Provide support for creating, managing, and fetching tournament and league details, including player participation, match results, and prize distributions.

ENDPOINTS:=

SignUp or Register: /auth/signUp
Login: /auth/login

Create Player: /player/create
Get All Player: /player/getAll
Get Player by Id: /player/get/{Id}
Get Players by TeamId: /player/getByTeam/{teamId}
Update Player: /player/update/{playerId}
Delete Player: /player/delete/{playerId}

Create Team: /team/create
Get All Teams: /team/getAll
Get Team by Id: /team/get/{Id}
Update Team: /team/update/{id}
Delete Team: /team/delete/{id}

Create Tournament: /tournament/create
Get All Tournaments: /tournament/getAll
Get Tournament by Id: /tournament/get/{Id}
Update Tournament: /tournamente/update/{id}
Delete Tournament: /tournament/delete/{id}
End Tournament: /tournament/endTournament/{tournamentId}
Get all Teams in a tournament: /tournament/getTeams/{tournamentId}
Get Ongoing Tournaments: /tournament/getOngoingTournaments
Get Upcoming Tournaments : /tournament/getUpcomingTournaments
Add Team to tournament: /tournament/addTeam
Delete Team from a tournament:/tournament/deleteTeam
Distribute Prize:/tournament/distributePrize

Create League: /league/create
Get All Leagues: /league/getAll
Get League by Id: /league/get/{Id}
Update League: /league/update/{id}
Delete League: /league/delete/{id}
Add Team to League: /league/addTeam
Delete Team from a League:/league/deleteTeam

Create Match: /match/create
Get All Matches: /match/getAll
Get Match by Id: /match/get/{Id}
Declare Result of a Match: /match/setResult
