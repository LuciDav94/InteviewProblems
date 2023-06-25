import SatelliteList from '~/components/List/SatelliteList';
import { Box, Grid } from '@mui/material';
import Header from '~/components/Header/Header';
import SatelliteMap from '~/components/Map/SatelliteMap';

function App() {
  return (
    <Box sx={{ flexGrow: 1 }} padding={10}>
      <Header />
      <Grid container spacing={2}>
        <Grid item xs={2}>
          <SatelliteList />
        </Grid>
        <Grid item xs={10}>
          <SatelliteMap />
        </Grid>
      </Grid>
    </Box>
  );
}

export default App;
