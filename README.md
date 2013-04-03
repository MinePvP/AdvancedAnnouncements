# Announcer for SpoutServer


Announcer is a Player Information Tool for the SpoutServer. You
can add / remove Messages for all Players / per World ore per Permission.

You can Output the Message per Order ore per Random and you can set a
Interval for the Output.

## Commands
- /announcer help
Get the Help
- /announcer list
List all Messages
- /announcer new
Create a new MessageGroup
- /announcer delete <MessageGroup Id>
Delete a MessageGroup
- /announcer add <MessageGroup Id> A new Message
Add a new Message
- /announcer remove <MessageGroup Id> <Message Id>
Remove a Message over his ID
- /announcer addworld <MessageGroup Id> world_name
Add a World where the Message will send to
- /announcer removeworld <MessageGroup Id> <World Id>
Remove a World over the Id
- /announcer permission <MessageGroup Id> <Permission>
Set a Permission where the Player has to need to recive the Message
- /announcer setchatchannel <MessageGroup Id> <chatchanell>
Set a ChatChannel where the Message will send
- /announcer random <MessageGroup Id> <true|false>
Set the Message output to Random ore in Order
- /announcer interval <MessageGroup Id> <time>
Set the Interval who send a Message
- /announcer reload
Reload the Configuration

## Permissions
- announcer.help
- announcer.list
- announcer.new
- announcer.delete
- announcer.message.add
- announcer.message.remove
- announcer.world.add
- announcer.world.remove
- announcer.set.permission
- announcer.set.random
- announcer.set.interval
- announcer.reload

## ChatStyle
- BLACK
- DARK_BLUE
- DARK_GREEN
- DARK_CYAN
- DARK_RED
- PURPLE
- GOLD
- GRAY
- DARK_GRAY
- BLUE
- BRIGHT_GREEN
- CYAN
- RED
- PINK
- YELLOW
- WHITE
- CONCEAL
- BOLD
- STRIKE_THROUGH
- UNDERLINE
- ITALIC
- RESET

- NEWLINE

## Configuration
``
Settings:
    Debug: false
    Interval: 60
    Prefix: '{{RED}}Announce {{WHITE}}: '
MessageGroups:
    '0':
        Worlds:
        - world
        - world_nether
        - the_end
        Permission: announcer.info
        Local: ''
        ChatChannel: ''
        Settings:
            Interval: 45
            Random: false
            Prefix: '{{YELLOW}}INFO {{WHITE}}: '
        Messages:
        - Server Restarts are at 6am / 12am / 6pm / 12pm
        - Read the News on www.minepvp.ath.cx
    '1':
        Worlds:
        - world
        - world_nether
        Permission: announcer.tips
        Local: de_DE
        ChatChannel: ''
        Settings:
            Interval: 90
            Random: true
            Prefix: '{{YELLOW}}Tips {{WHITE}}: '
        Messages:
        - Get a new Plot with /plot create
        - Get a new City with /city create
    '2':
        Worlds:
        - world
        - world_nether
        - the_end
        Permission: announcer.rules
        Local: ''
        ChatChannel: ''
        Settings:
            Interval: 20
            Random: false
            Prefix: '{{YELLOW}}RULES {{WHITE}}: '
        Messages:
        - RULE 1
        - RULE 2
        - RULE 3
        - RULE 4
        - RULE 5
        - RULE 6
        - RULE 7
        - RULE 8
        - RULE 9
``


## Changelog

### Version 0.7
- update for SpoutAPI
- remove execute Commands
- bugfixes

### Version 0.6
- MySQL Support
- cleanup
- bugfixes

### Version 0.5
- add some Local Stuff for send Messages only to Player with the same Local
- add Multiline Messages with <newline>
- add some ChatChannel stuff to Support it in a next Version
- fix some Bugs

### Version 0.4
- add Commands for new Configuration

### Version 0.3
- new Config with MessageGroups
- Commands removed will come in the next Version again

### Version 0.2
- add Translation System

### Version 0.1
- initial Releas