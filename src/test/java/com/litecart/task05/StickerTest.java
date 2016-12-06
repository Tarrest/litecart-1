package com.litecart.task05;

import com.litecart.task02.TestBase;
import org.openqa.selenium.By;
import org.testng.annotations.Test;

/**
 * Created by pshynin on 11/26/16.
 */
public class StickerTest extends TestBase {

    @Test
    public void testSticker() {
    wd.findElement(By.cssSelector("[class=fa fa-chevron-circle-left]")).click();
    }
}