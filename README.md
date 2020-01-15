## Intro
This is a **Telegram bot** that is used as an admin of a channel.  
When a user starts a chat with the bot, bot sends messages to a private channel that all the admins of channel are there.  
So admins can see messages without being in chat with user directly and can send answers to users. 
There is also a java **Logger** that saves messages to a file.
  
### Format of the PM's
```
[username]: @username
[firstname]: Jack
[lastname]: Majidi
[JackMajidi says]:
message 😂 message 😆 message 😱
message message message 😶.
```
  
## Usage
1. Create a bot using [**BotFather**](https://t.me/botfather).  
2. Add your bot to your channel and give it administrator permissions.  
3. Clone project and import it using Maven.   
4. In class Main replace ``myBotUsername`` ``myBotToken`` ``myChatId``.(chatId is your channels id) 
```
botsApi.registerBot(new MyBot("myBotUsername","myBotToken","myChatId"));
```
Run the program! :)

### Resources
[Bots: An introduction for developers](https://core.telegram.org/bots)  
[Telegram Bot API](https://core.telegram.org/bots/api)  
[Sending a message to a Telegram channel](https://medium.com/@xabaras/sending-a-message-to-a-telegram-channel-the-easy-way-eb0a0b32968)

## Yet to come
- Ability of answering users using the bot and inline queries
- Sending stickers, images, Gifs and ... via bot
- Alerting when a message is edited by user
- Deleting a message that a user has deleted in chat with bot
