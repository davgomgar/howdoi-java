package es.dgg.howdoi;


import es.dgg.howdoi.google.LinkResultParser;
import es.dgg.howdoi.stackoverflow.StackOverflowLink;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.junit.Before;
import org.junit.Test;

import java.util.Collections;
import java.util.List;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class ResultsParserTest {

    private LinkResultParser parser;
    private Elements mockedElements;

    @Before
    public void configure() {
        mockedElements = mock(Elements.class);
        parser = new LinkResultParser(mockedElements);
    }

    @Test
    public void returns_an_empty_collection_when_there_are_no_links() throws  Exception {
        when(mockedElements.stream()).thenReturn(Stream.<Element>empty());

        assertThat(parser.parse()).isEqualTo(Collections.emptyList());
    }

    @Test
    public void returns_empty_when_does_elements_does_not_match_selection() throws  Exception {
        //given
        Element mockedElement1 = mock(Element.class);
        Element mockedElement2 = mock(Element.class);
        when(mockedElement1.attr("href")).thenReturn("/url/tcp://fakeurl.com");
        when(mockedElement2.attr("href")).thenReturn("/invalid/url");
        when(mockedElements.stream()).thenReturn(Stream.of(mockedElement1, mockedElement2));
        //then
        assertThat(parser.parse()).isEqualTo(Collections.emptyList());
    }

    @Test
    public void returns_filtered_collection_for_well_formed_links() throws  Exception {
        //given
        Element mockedElement1 = mock(Element.class);
        Element mockedElement2 = mock(Element.class);
        Element mockedElements3 = mock(Element.class);
        when(mockedElement1.attr("href")).thenReturn("/url/http://fakeurl.com/demo/776");
        when(mockedElement2.attr("href")).thenReturn("/url/https://stackoverflow.com/some_resource/88");
        when(mockedElements3.attr("href")).thenReturn("/invalid/url");
        when(mockedElements.stream()).thenReturn(Stream.of(mockedElement1, mockedElement2));
        //then
        StackOverflowLink link1 = new StackOverflowLink("http://fakeurl.com/demo/776");
        StackOverflowLink link2 = new StackOverflowLink("https://stackoverflow.com/some_resource/88");
        List<StackOverflowLink> parsedValues = parser.parse();
        assertThat(parsedValues).isNotEqualTo(Collections.emptyList());
        assertThat(parsedValues).hasSize(2).extracting("url").contains(link1.getUrl(), link2.getUrl());
    }
}