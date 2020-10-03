package themcbros.uselessmod;

import themcbros.uselessmod.api.UselessAPI;
import themcbros.uselessmod.api.init.IUselessItemList;
import themcbros.uselessmod.init.ItemInit;

/**
 * @author TheMCBrothers
 */
public class UselessModAPI implements UselessAPI.IUselessAPI {
    UselessModAPI() {
    }

    @Override
    public IUselessItemList getItems() {
        return ItemInit.INSTANCE;
    }
}
