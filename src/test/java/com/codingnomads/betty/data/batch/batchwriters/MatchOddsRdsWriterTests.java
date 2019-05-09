package com.codingnomads.betty.data.batch.batchwriters;

import com.codingnomads.betty.data.models.MatchOdds;
import com.codingnomads.betty.logic.interfaces.MatchOddsJpaRepository;
import org.junit.Before;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;

import static org.mockito.Mockito.*;

public class MatchOddsRdsWriterTests {

    private MatchOddsRdsWriter testMatchOddsRdsWriter;
    private MatchOddsJpaRepository mockMatchOddsJpaRepository;
    private MatchOdds matchOdds;
    private List<MatchOdds> list = Arrays.asList(matchOdds);
    private List<List<MatchOdds>> items;

    @Before
    public void setUp(){

        mockMatchOddsJpaRepository = mock(MatchOddsJpaRepository.class);
        testMatchOddsRdsWriter = new MatchOddsRdsWriter(mockMatchOddsJpaRepository);
        matchOdds = new MatchOdds();
        list = Arrays.asList(matchOdds);
        items = Arrays.asList(list);
    }

    @Test
    public void whenWriteRun_shouldCallMatchOddsJpaRepositorySaveMethod() throws Exception {

        when(mockMatchOddsJpaRepository.saveAll(list)).thenReturn(list);

        testMatchOddsRdsWriter.write(items);

        verify(mockMatchOddsJpaRepository, times(1)).saveAll(list);
    }
}
