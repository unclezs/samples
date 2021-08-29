package com.unclezs.simples.datafx.tutorial2;

import io.datafx.controller.FXMLController;
import io.datafx.controller.flow.action.LinkAction;
import javafx.scene.control.Button;

/**
 * @author blog.unclezs.com
 * @since 2021/02/08 15:16
 */
@FXMLController("/tutorial2/view2.fxml")
public class View2Controller {
  @LinkAction(View1Controller.class)
  public Button button;
}
