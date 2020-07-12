package playground;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InOrder;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class PlaygroundTest {

    @Mock
    private DummyCalculator mockedDummyCalculator;

    @InjectMocks
    private DummyService dummyService;

    @Test
    public void shouldAddSuccessfullyWithoutMock() {
        DummyCalculator dummyCalculator = new DummyCalculator();

        int result = dummyCalculator.plus(1,2);

        assertThat(result).isEqualTo(3);
    }

    @Test
    public void shouldAddSuccessfullyUsingMockProgrammatically() {
        DummyCalculator dummyCalculator = mock(DummyCalculator.class);

        when(dummyCalculator.plus(1,2)).thenReturn(3);

        int result = dummyCalculator.plus(1,2);

        assertThat(result).isEqualTo(3);
    }

    @Test
    public void shouldAddSuccessfullyUsingMockAnnotation() {
        when(mockedDummyCalculator.plus(1,2)).thenReturn(3);

        int result = mockedDummyCalculator.plus(1,2);

        assertThat(result).isEqualTo(3);
    }

    @Test
    public void shouldAddSuccessfullyUsingInjectMocks() {
        when(mockedDummyCalculator.plus(1,2)).thenReturn(3);

        int result = dummyService.dummySum(1, 2);

        assertThat(result).isEqualTo(3);
    }

    @Test
    public void shouldCalculateDummySumLogicSuccessfully() {
        when(mockedDummyCalculator.plus(1,2)).thenReturn(3);
        when(mockedDummyCalculator.plus(3,4)).thenReturn(7);

        int result = dummyService.dummySumLogic(1, 2, 3, 4);

        assertThat(result).isEqualTo(10);
    }

    @Test
    public void shouldVerifyDummySumLogic() {
        when(mockedDummyCalculator.plus(1,2)).thenReturn(3);
        when(mockedDummyCalculator.plus(3,4)).thenReturn(7);

        int result = dummyService.dummySumLogic(1, 2, 3, 4);

        assertThat(result).isEqualTo(10);

        verify(mockedDummyCalculator, times(2)).plus(anyInt(), anyInt());

        InOrder inOrder = Mockito.inOrder(mockedDummyCalculator);
        inOrder.verify(mockedDummyCalculator).plus(1,2);
        inOrder.verify(mockedDummyCalculator).plus(3,4);
    }

}
