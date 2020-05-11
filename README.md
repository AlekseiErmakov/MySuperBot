# MySuperBot
Пример создания многофункционального телеграм бота на java c использованием моей небольшой [TelegramBotLibrary](https://github.com/lexLaeda/TelegramBotLibrary) 
и Spring FrameWork.
Список реализованных комманд : 
1. [StartCommand](https://github.com/lexLaeda/MySuperBot/blob/master/src/main/java/application/commands/StartCommand.java) - выводит приветственное сообщение.
2. [HelpCommand](https://github.com/lexLaeda/MySuperBot/blob/master/src/main/java/application/commands/HelpCommand.java) - выводит список доступных команд, 
кроме добавленныхв исключение. 
1. [WikiCommand](https://github.com/lexLaeda/MySuperBot/blob/master/src/main/java/application/commands/WikiCommand.java) - в ответ 
на запрос пользователя(любое существительное) в чате отправляет ответ ввиде его определения.
2. [RateCommand](https://github.com/lexLaeda/MySuperBot/blob/master/src/main/java/application/commands/valute/RateCommand.java) - в ответ
на запрос пользователя(любая свободно конвертируемая валюта) отправляет ее курс в рублях по последним данным Центрального Банка Российской Федерации.
3. [PassCommand](https://github.com/lexLaeda/MySuperBot/blob/master/src/main/java/application/commands/PasswordCommand.java) - в ответ на 
запрос пользователя(целое число в диапазоне от 3 до 15) оптправляет сгенерированный пароль заданного размера.
4. [CityGameCommand](https://github.com/lexLaeda/MySuperBot/blob/master/src/main/java/application/commands/CityGameCommand.java) - запускает игру
в города[CityGame](https://github.com/lexLaeda/MySuperBot/blob/master/src/main/java/application/scripts/cities/CitiesGame.java) пока команда 
[StopCommand](https://github.com/lexLaeda/MySuperBot/blob/master/src/main/java/application/commands/StopCommand.java) не будет вызвана,
поддерживается игра на русском и английском языке.
