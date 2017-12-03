import React from 'react';
import {Component} from 'react';

import {Container, Button, Form, FormGroup, Label, Input, FormText } from 'reactstrap';

export class Main extends Component {
    render() {
        return (
            <Container fluid={true}>
                <p className="contentTitle">Showcase</p>

                <h4>Dodwanie nowego ekranu</h4>
                <ul>
                    <li>Tworzymy sobie nowy pakiet w katalogu app/modules (jeżeli jeszcze nie istnieje)</li>
                    <li>W pakiecie tworzymy nowy plik TWOJA_NAZWA.jsx</li>
                    <li>W pliku <code>app/App.jsx</code> dodajemy importemy sobie nasz plik <code>{"import {NAZWA_TWOJEJ_KLASY} from './modules/TWOJ_PAKIET/TWOJA_NAZWA.jsx"}</code> (końcówka .jsx musi być)</li>
                    <li>W pliku <code>app/App.jsx</code> dodajmy nową ścieżke do Routera <code>{'<Route exact path="link_przeglądarkowy" component={NAZWA_TWOJEJ_KLASY}/>'}</code></li>
                    <li>Po wejściu pod adres zdefiniowany powyżej jako <code>link_przeglądarkowy</code> wyświetli się Twój komponent</li>
                </ul>

                <h4>Kontrolki</h4>
                <h5>Lista kontrolek dostępnych do użycia:</h5>
                <a href="https://reactstrap.github.io/components">ReactStrap</a>
                <p>Gdyby jakiejś kontrolki nie było od razu gotowej w postaci komponentu - można zdefiniować ręcznie zgodnie z dokumentacją bootstrapa: <a href="https://getbootstrap.com/docs/4.0/getting-started/introduction/">Bootstrap Dokumentacja</a></p>

                <h5>Randomowe kontrolki</h5>
                <h6 className="mt-1">Przyciski</h6>
                <Button color="primary">Jasne</Button>{' '}
                <Button color="success">że</Button>{' '}
                <Button color="info">to</Button>{' '}

                <h6 className="mt-1">Pola formularzy</h6>
                <Form>
                    <FormGroup>
                        <Label for="exampleEmail">Zwykły input</Label>
                        <Input type="text" name="jakistam" id="musiBycUnikalne" placeholder="with a placeholder" />
                    </FormGroup>
                    <FormGroup>
                        <Label for="examplePassword">Z gwiazdkami :)</Label>
                        <Input type="password" name="password" id="examplePassword" placeholder="password placeholder" />
                    </FormGroup>
                    <FormGroup>
                        <Label for="exampleSelect">Select (np. do wyboru wartości słownikowych)</Label>
                        <Input type="select" name="select" id="exampleSelect">
                            <option>Mężczyzna</option>
                            <option>Kobieta</option>
                            <option>Mężczyzna ale identyfikuje się jako kobieta</option>
                            <option>Kobieta ale identyfikuje się jako mężczyzna</option>
                            <option>Nieokreślono</option>
                        </Input>
                    </FormGroup>
                    <FormGroup>
                        <Label for="exampleText">Pole do wprowadzania długiego tekstu</Label>
                        <Input type="textarea" name="text" id="exampleText" />
                    </FormGroup>

                    <FormGroup check>
                        <Label check>
                            <Input type="checkbox" />{' '}
                            Tak sie robi checkboxa w formularzu
                        </Label>
                    </FormGroup>
                </Form>

                <h5>sposób użycia możecie podpatrzyć na tym ekranie, czyli: FE\App\modules\main\Main.jsx</h5>
                <p className="font-weight-bold">Generalnie robi się to tak:</p>
                <ul>
                    <li>Importujecie kontrolkę np. <code>{"import {Container, Button} from 'reactstrap';"}</code> - to jest import kontrolki Container oraz Button</li>
                    <li>W metodzie <code>render()</code> wstawiacie tag z nazwą taką jak zaimportowana kontrolka - np. <code>{""}</code></li>
                </ul>

                <h4>Jak używać TABELI</h4>
                <p>Tabela zawiera sortowanie i paginacje, kiedyś dorobi się jeszcze ewentualnie wyszukiwanie po wartościach w kolmunach.</p>
                <p className="font-weight-bold">Przykładową tabelkę możecie zobaczyć tutaj: <code>FE\app\modules\employee\List.jsx</code></p>

                <h5>A robi się to tak:</h5>
                <ul>
                    <li>Importujecie komponent tabeli <code>{"import {PostPageableTable} from '../../components/PostPageableTable/PostPageableTable.jsx';"}</code></li>
                    <li>Należy zdefiniować kolumny jakie występują, w tym celu najlepiej dodać sobie metodę <code>getHeaderDefinition</code> i zwracać tablicę obiektów, gdzie każdy obiekt to definicja kolejnej kolumny. Dostępne pola:
                        <ul>
                            <li>key - klucz pola - musi być zgodny ze zwracanym modelem, zagnieżdzenia piszemy z kropką</li>
                            <li>label - to co bedzie się wyświetlać w nagłowku kolumny</li>
                            <li>sortCode - kod sortowania (opcjonalne)</li>
                            <li>sortDirection - kierunek sortowania ASC lub DESC (opcjonalne)</li>
                            <li>format - w przypadku gdy chcemy formatować wartość przed wyświetleniem, możliwe definicje:
                                <ul>
                                    <li>date - formatowanie jako DD-MM-RRRR</li>
                                    <li>time - formatowanie jako HH:MM:SS</li>
                                    <li>datetime - formatowanie jako DD-MM-RRRR HH:MM:SS</li>
                                    <li>{'{type: "dictionary", dictionaryName: "nazwaSlownika"}'} - w przypadku wartości słownikowych, wskazujemy nazwe słownika - komponent tabeli sam sobie go pobierze i podmieni kody słownikowe na ładne labelki </li>
                                </ul>
                            </li>
                        </ul>
                    </li>
                    <li>W metodzie <code>render()</code> swojego komponentu wstawiacie <code>{'<PostPageableTable headerDefinition=TABLICA_Z_DEFINICJĄ_KOLUMN dataUrl="TU_DAJ_ADRES_USLUGI"/>'}</code></li>
                    <li>...i już działa</li>
                </ul>

            </Container>
        );
    }
}