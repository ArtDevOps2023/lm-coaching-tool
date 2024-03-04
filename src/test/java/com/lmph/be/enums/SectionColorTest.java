package com.lmph.be.enums;

import static org.assertj.core.api.Assertions.assertThat; 
 
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * Section Color Enum Unit Test
 * @author Ryan Valmoria
 */
@SpringBootTest
class SectionColorTest {
 
	/*
	 * Unit test to check if correct color label is returned
	 */
	@Test
	void getLabelOfColor_ReturnCorrectValue() {
		String blue = SectionColor.getLabelOfColor("B");
		String green = SectionColor.getLabelOfColor("G");
		String yellow = SectionColor.getLabelOfColor("Y");
		String colorNotFound = SectionColor.getLabelOfColor("A");
		String noParamPassed = SectionColor.getLabelOfColor("");
		
		assertThat(blue).isEqualTo("Blue");
		assertThat(green).isEqualTo("Green");
		assertThat(yellow).isEqualTo("Yellow");
		assertThat(colorNotFound).isNull();
		assertThat(noParamPassed).isNull();
	}

}
