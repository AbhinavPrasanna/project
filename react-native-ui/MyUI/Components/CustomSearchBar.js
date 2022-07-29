import * as React from 'react';
import { Platform } from 'react-native';
import { SearchBar } from 'react-native-elements';


export default function CustomSearchBar(props) {
    return (
        <SearchBar
            placeholder="Search"
            onChangeText={props.onChangeSearch}
            value={props.searchQuery}
            containerStyle={{ backgroundColor: '#f4f4f4', paddingLeft: 3, paddingRight: 4 }}
            inputContainerStyle={{
                backgroundColor: '#ffffff',
                borderRadius: 30,
                borderColor: 'black',
                borderTopWidth: .5,
                borderRightWidth: .5,
                borderBottomWidth: 5,
                borderLeftWidth: 5,
            }}
            platform={Platform.OS}
            cancelButtonProps={{ color: '#000000' }}
        />
    )
}