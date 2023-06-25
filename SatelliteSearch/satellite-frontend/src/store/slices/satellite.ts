import type { PayloadAction } from '@reduxjs/toolkit';
import { createSlice } from '@reduxjs/toolkit';

import type { RootState } from '../store';
import { Satellite } from '~/types/satellite';

export interface SatelliteState {
  currentEntry: Satellite | null;
  list: Satellite[];
  updateSearch: boolean;
  filterText: string;
}

const initialState: SatelliteState = {
  list: [],
  currentEntry: null,
  updateSearch: false,
  filterText: '',
};

export const satelliteSlice = createSlice({
  name: 'satellite',
  initialState,
  reducers: {
    setCurrentEntry: (state, action: PayloadAction<Satellite | null>) => {
      state.currentEntry = action.payload;
    },
    setFilterText: (state, action: PayloadAction<string>) => {
      state.filterText = action.payload;
      state.updateSearch = !state.updateSearch;
    },
    setUpdateSearch: (state) => {
      state.updateSearch = !state.updateSearch;
    },
    setList: (state, action: PayloadAction<Satellite[]>) => {
      state.list = action.payload;
    },
  },
});

export const { setCurrentEntry, setFilterText, setList, setUpdateSearch } = satelliteSlice.actions;

export const selectCurrentEntry = (state: RootState) => state.satellite.currentEntry;
export const selectUpdateSearch = (state: RootState) => state.satellite.updateSearch;
export const selectFilterText = (state: RootState) => state.satellite.filterText;
export const selectList = (state: RootState) => state.satellite.list;
