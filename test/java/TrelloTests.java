import com.sun.org.apache.bcel.internal.generic.ATHROW;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;

import java.io.IOException;

public class TrelloTests {

    private String boardId;
    private String listId;
    private String cardId;
    RetrofitBuilder retrofitBuilder = new RetrofitBuilder();
    Params params = new Params();

//  Create Board
    @Test (priority = 1)
    public void checkCreateBoard() throws IOException {
        String name = "Board";
        Params createdBoard = retrofitBuilder.getTrelloApi().createBoard(params, name).execute().body();

        boardId = createdBoard.getId();

        Assert.assertEquals(createdBoard.getName(), name);
    }

//  Create List
    @Test (priority = 2)
    public void checkCreateList() throws IOException{
        String nameList = "List";
        Params createdList = retrofitBuilder.getTrelloApi().createList(params,nameList,boardId).execute().body();

        listId = createdList.getId();

        Assert.assertEquals(createdList.getName(), nameList);
    }

//  Create Card
    @Test (priority = 3)
    public void checkCreateCard() throws IOException{
        Params createdCard = retrofitBuilder.getTrelloApi().createCard(params,listId).execute().body();

        cardId = createdCard.getId();

        Assert.assertEquals(createdCard.getName(),"");
    }

//  Get Card
    @Test (priority = 4)
    public void checkGetCard() throws IOException {
        int code = retrofitBuilder.getTrelloApi().getCard(cardId,params.getKey(),params.getToken()).execute().code();
        Assert.assertEquals(code,200);
    }

//  Update Card
    @Test (priority = 5)
    public void checkUpdateCard() throws IOException{
        String name = "Update Card";
        Params updatedCard = retrofitBuilder.getTrelloApi().upgradeCard(cardId,params.getKey(),params.getToken(),name).execute().body();

        Assert.assertEquals(updatedCard.getName(),name);
    }
//  Delete Card
    @Test (priority = 6)
    public void checkDeleteCard() throws IOException{
        int code = retrofitBuilder.getTrelloApi().deleteCard(cardId,params.getKey(),params.getToken()).execute().code();

        Assert.assertEquals(code,200);
    }
}
