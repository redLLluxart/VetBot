package telegram;

import lombok.SneakyThrows;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.AnswerCallbackQuery;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import shedule.SheduleFunctions;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;


public class Bot extends TelegramLongPollingBot {

    String group = null;

    private Properties getPropertyFromFile() {

        String rootPath = "src/main/resources/config.properties";
        Properties props = new Properties();
        try {
            props.load(new FileInputStream(rootPath));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return props;
    }


    @Override
    public String getBotUsername() {
        return getPropertyFromFile().getProperty("name");
    }

    @Override
    public String getBotToken() {
        return getPropertyFromFile().getProperty("token");
    }

    @SneakyThrows
    @Override
    public void onUpdateReceived(Update update) {
        if (update.hasMessage() && update.getMessage().getText().equals("/start")) { // check hasMessage first! if not check hasMessage(), it call getMessage(), it breaks into runtimeException
            String chatId = String.valueOf(update.getMessage().getChatId());
            sendMessage("Все пары проходят в промежутке 18:00 - 21:15.\n(Гл) Главный корпус\n(Им) Здание каф. Иммунологии\n" +
                    "(Ак) Здание каф. Акушерства\n(Улк) Учебно-Лабораторный корпус\n" +
                    "(Ан) Анатомический корпус\n(Дос) Дос-2; (Лб) Лаборатория биофизики\n", chatId);
            sendMarkup(CreateMenu.createMenuMarkupGroup(), chatId, "Привет!\n" +
                    "Чтобы получить расписание выбери группу в которой ты учишься.");
        } else if (update.hasCallbackQuery()) {
            handleCallback(update);
        }
    }

    private void handleCallback(Update update) throws TelegramApiException, IOException {
        String callData = update.getCallbackQuery().getData();
        String chatId = String.valueOf(update.getCallbackQuery().getMessage().getChatId());
        String queryId = update.getCallbackQuery().getId();
        AnswerCallbackQuery close = AnswerCallbackQuery.builder()
                .callbackQueryId(queryId).build();
        switch (callData) {
            case "1":
                sendMessage("Вы выбрали группу 1.", chatId);
                group = "1";
                execute(close);
                sendMarkup(CreateMenu.createMenuMarkupPeriod(), chatId, "Выбери период времени для которого тебе требуется расписание.");
                break;
            case "2":
                sendMessage("Вы выбрали группу 2.", chatId);
                group = "2";
                execute(close);
                sendMarkup(CreateMenu.createMenuMarkupPeriod(), chatId, "Выбери период времени для которого тебе требуется расписание.");
                break;
            case "3":
                sendMessage("Вы выбрали группу 3.", chatId);
                group = "3";
                execute(close);
                sendMarkup(CreateMenu.createMenuMarkupPeriod(), chatId, "Выбери период времени для которого тебе требуется расписание.");
                break;
            case "4":
                sendMessage("Вы выбрали группу 4.", chatId);
                group = "4";
                execute(close);
                sendMarkup(CreateMenu.createMenuMarkupPeriod(), chatId, "Выбери период времени для которого тебе требуется расписание.");
                break;
            case "Today":
                sendMessage("Сегодня у вас будет:\n" + SheduleFunctions.getTodayShedule(group), chatId);
                execute(close);
                break;
            case "Next lesson":
                sendMessage("Ваше следующее занятие:\n" + SheduleFunctions.getNextShedule(group), chatId);
                execute(close);
                break;
            case "This week":
                sendMessage("Ваше расписание на эту неделю:\n" + SheduleFunctions.getTodayWeekShedule(group).toString(), chatId);
                execute(close);
                break;
            default:
                break;
        }
    }

    private void sendMessage(String msg, String chatId) {
        SendMessage sendMessage = new SendMessage(chatId, msg);
        try {
            execute(sendMessage);
        } catch (TelegramApiException e) {
            throw new RuntimeException(e);
        }
    }

    private void sendMarkup(InlineKeyboardMarkup menuMarkup, String chatId, String text) {
        SendMessage sendMessage = new SendMessage();
        sendMessage.setChatId(chatId);
        sendMessage.setText(text); // if not set Text message, it won't send
        sendMessage.setReplyMarkup(menuMarkup);
        try {
            execute(sendMessage);
        } catch (TelegramApiException e) {
            throw new RuntimeException(e);
        }
    }

}
