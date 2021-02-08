package com.unclezs.simples.datafx.tutorial2;

import io.datafx.controller.FXMLController;
import io.datafx.controller.flow.action.LinkAction;
import javafx.scene.control.Button;

/**
 * @author blog.unclezs.com
 * @since 2021/02/08 15:16
 */
@FXMLController("/tutorial2/view1.fxml")
public class View1Controller {
    @LinkAction(View2Controller.class)
    public Button button;
}
