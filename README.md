# Quiz153

ButtonTest(PASS) tester at vi kommer inn i databasen fra hovedmenyen. 
Denne testen trykker på "Database" knappen i hovedmenyen, og at brukeren blir videresendt til DatabaseActivity. :)

DeleteTest (PASS) tester om vi får slettet en QuizEntry fra databasen vår.
Denne testen trykker på "DELETE" knappen i databaseActivity og sjekker om at elementet blir slettet fra databasen.

QuizActivityTest(PASS) tester om scoren blir oppdatert rikitg ved både korrekt og feil svar.
Denne testen kjører gjennom en quiz, og sjekker om at scoren blir riktig oppdatert fra databasen og persister skikkelig.

Når vi prøver å kjøre apktool får vi ut denne:
Using Apktool 2.7.0 on app-debug.apk
I: Loading resource table...
I: Decoding AndroidManifest.xml with resources...
I: Loading resource table from file: C:\Users\iseli\AppData\Local\apktool\framework\1.apk
I: Regular manifest package...
I: Decoding file-resources...
I: Decoding values / XMLs...
I: Baksmaling classes.dex...
I: Baksmaling classes2.dex...
I: Baksmaling classes3.dex...
I: Baksmaling classes4.dex...
I: Baksmaling classes5.dex...
I: Copying assets and libs...
I: Copying unknown files...
I: Copying original files...
I: Copying META-INF/services directory

Utifra dette, ser vi at den bruker 1.apk filen under testing. 

Vi har ikke funnet noen adb kommando...