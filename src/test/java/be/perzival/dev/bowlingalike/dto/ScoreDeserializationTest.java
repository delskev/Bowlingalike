package be.perzival.dev.bowlingalike.dto;

import be.perzival.dev.bowlingalike.mapper.ScoreDTOMapper;
import be.perzival.dev.bowlingalike.model.Frame;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class ScoreDeserializationTest {

    @Autowired
    public ObjectMapper objectMapper;


    @Test
    void noSpareOrStrikeSerializationTest() throws JsonProcessingException {
        //given
        Frame frame = new Frame();
        frame.updatePinsLeftAndScore(4);
        frame.updatePinsLeftAndScore(3);
        frame.updatePinsLeftAndScore(7);
        String expected = "{\"frame\":1,\"shoot1\":\"4\",\"shoot2\":\"3\",\"shoot3\":\"7\",\"score\":14}";

        //when
        String actual = objectMapper.writeValueAsString(ScoreDTOMapper.toJson(frame));
        //then
        Assertions.assertThat(actual).isEqualTo(expected);
    }

    @Test
    void spareSerializationTest() throws JsonProcessingException {
        //given
        Frame frame = new Frame();
        frame.updatePinsLeftAndScore(4);
        frame.updatePinsLeftAndScore(11);
        String expected = "{\"frame\":1,\"shoot1\":\"4\",\"shoot2\":\"/\",\"score\":15}";

        //when
        String actual = objectMapper.writeValueAsString(ScoreDTOMapper.toJson(frame));
        //then
        Assertions.assertThat(actual).isEqualTo(expected);
    }

    @Test
    void spareSerializationTest2() throws JsonProcessingException {
        //given
        Frame frame = new Frame();
        frame.updatePinsLeftAndScore(4);
        frame.updatePinsLeftAndScore(5);
        frame.updatePinsLeftAndScore(6);
        String expected = "{\"frame\":1,\"shoot1\":\"4\",\"shoot2\":\"5\",\"shoot3\":\"/\",\"score\":15}";

        //when
        String actual = objectMapper.writeValueAsString(ScoreDTOMapper.toJson(frame));
        //then
        Assertions.assertThat(actual).isEqualTo(expected);
    }

    @Test
    void strikeSerializationTest() throws JsonProcessingException {
        //given
        Frame frame = new Frame();
        frame.updatePinsLeftAndScore(15);
        String expected = "{\"frame\":1,\"shoot1\":\"X\",\"score\":15}";

        //when
        String actual = objectMapper.writeValueAsString(ScoreDTOMapper.toJson(frame));
        //then
        Assertions.assertThat(actual).isEqualTo(expected);
    }
}
