package com.epam.infohandling;

import com.epam.infohandling.data.DataReader;
import com.epam.infohandling.exception.DataException;
import org.junit.Assert;
import org.junit.Test;

public class DataReaderTest {
    private static final String VALID_PATH = "src/main/resources/text.txt";
    private static final String INVALID_PATH = "incorrect path";

    private static final String EXPECTED_TEXT = "It has survived - not only (five) centuries, but also the leap into [13  2 +] electronic typesetting, remaining [3  5 +] essentially [15  3 /] unchanged. It was popularised in the [5 x *] with the release of Letraset sheets containing Lorem Ipsum passages, and more recently with desktop publishing software like Aldus PageMaker including versions of Lorem Ipsum.\n" +
            "        \tIt is a long established fact that a reader will be distracted by the readable content of a page when looking at its layout. The point of using [2 3 * y +] Ipsum is that it has a more-or-less normal distribution of letters, as opposed to using (Content here), content here, making it look like readable English.\n" +
            "        \tIt is a [1200  5 /] established fact that a reader will be of a page when looking at its layout.\n" +
            "        \tBye.\n";

    
    @Test
    public void testReadShouldReturnCorrectTextWhenFileIsValid() throws DataException {
        //given
        DataReader dataReader = new DataReader();

        //when
        String actualText = dataReader.read(VALID_PATH);

        //then
        Assert.assertEquals(EXPECTED_TEXT, actualText);
    }

    @Test(expected = DataException.class)
    public void testReadShouldThrowExceptionWhenFilePathIsInvalid() throws DataException {
        //given
        DataReader dataReader = new DataReader();

        //when
        String actualText = dataReader.read(INVALID_PATH);
    }

}
