package com.codingnomads.betty.data.batch.batchwriters;

import com.codingnomads.betty.data.batch.matchoddsjob.MatchOddsRdsWriter;
import com.codingnomads.betty.data.models.MatchOdds;
import com.codingnomads.betty.logic.services.MatchOddsService;
import org.junit.Before;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;

import static org.mockito.Mockito.*;

public class MatchOddsRdsWriterTests {

    private MatchOddsService mockMatchOddsService;
    private MatchOddsRdsWriter testMatchOddsRdsWriter;
    private MatchOdds matchOdds;
    private List<MatchOdds> list = Arrays.asList(matchOdds);
    private List<List<MatchOdds>> items;

    @Before
    public void setUp(){

        mockMatchOddsService = mock(MatchOddsService.class);
        testMatchOddsRdsWriter = new MatchOddsRdsWriter(mockMatchOddsService);
        matchOdds = new MatchOdds();
        list = Arrays.asList(matchOdds);
        items = Arrays.asList(list);
    }

    @Test
    public void whenWriteRun_shouldCallMatchOddsJpaRepositorySaveMethod() throws Exception {

        when(mockMatchOddsService.saveMatchOddsList(list)).thenReturn(list);

        testMatchOddsRdsWriter.write(items);

        verify(mockMatchOddsService, times(1)).saveMatchOddsList(list);
    }
}
