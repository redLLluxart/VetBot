package telegram;

import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;

import java.util.ArrayList;
import java.util.List;

public class CreateMenu {
    protected static InlineKeyboardMarkup createMenuMarkupGroup() {
        List<List<InlineKeyboardButton>> inlineButtons = new ArrayList<>();
        List<InlineKeyboardButton> inlineKeyboardButtonList1 = new ArrayList<>();

        InlineKeyboardButton inlineKeyboardButton1 = InlineKeyboardButton.builder()
                .text("1")
                .callbackData("1")
                .build();

        InlineKeyboardButton inlineKeyboardButton2 = InlineKeyboardButton.builder()
                .text("2")
                .callbackData("2")
                .build();

        InlineKeyboardButton inlineKeyboardButton3 = InlineKeyboardButton.builder()
                .text("3")
                .callbackData("3")
                .build();
        InlineKeyboardButton inlineKeyboardButton4 = InlineKeyboardButton.builder()
                .text("4")
                .callbackData("4")
                .build();


        inlineKeyboardButtonList1.add(inlineKeyboardButton1);
        inlineKeyboardButtonList1.add(inlineKeyboardButton2);
        inlineKeyboardButtonList1.add(inlineKeyboardButton3);
        inlineKeyboardButtonList1.add(inlineKeyboardButton4);
        inlineButtons.add(inlineKeyboardButtonList1);

        return InlineKeyboardMarkup
                .builder()
                .keyboard(inlineButtons)
                .build();
    }
    protected static InlineKeyboardMarkup createMenuMarkupPeriod() {
        List<List<InlineKeyboardButton>> inlineButtons = new ArrayList<>();
        List<InlineKeyboardButton> inlineKeyboardButtonList1 = new ArrayList<>();

        InlineKeyboardButton inlineKeyboardButton1 = InlineKeyboardButton.builder()
                .text("Сегодня")
                .callbackData("Today")
                .build();

        InlineKeyboardButton inlineKeyboardButton2 = InlineKeyboardButton.builder()
                .text("Следующее занятие")
                .callbackData("Next lesson")
                .build();

        InlineKeyboardButton inlineKeyboardButton3 = InlineKeyboardButton.builder()
                .text("Текущая неделя")
                .callbackData("This week")
                .build();


        inlineKeyboardButtonList1.add(inlineKeyboardButton1);
        inlineKeyboardButtonList1.add(inlineKeyboardButton2);
        inlineKeyboardButtonList1.add(inlineKeyboardButton3);
        inlineButtons.add(inlineKeyboardButtonList1);

        return InlineKeyboardMarkup
                .builder()
                .keyboard(inlineButtons)
                .build();
    }
}
