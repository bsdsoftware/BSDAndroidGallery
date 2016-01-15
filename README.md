# BSDAndroidGallery
Visualizzatore android di immagini e file tramite modale o fragment in vista a griglia o a image switcher.



Utilizzo
-----
Per utilizzarlo è necessario aggiungere nel manifest il permesso di lettura nella memoria esterna

```xml
  <uses-permission android:name="android.permission.WRITE_EXTERNAL_STORAGE" />
```
Bisogna creare un fragment di tipo BSDImageGridDialog oppure BSDImageSwitcherDialogFragment.
Si può caricare come fragment normale oppure caricarlo come dialog:
```java

  BSDImageGridDialogFragment gridDialogFragment = new BSDImageGridDialogFragment();
  gridDialogFragment.setGallery(gallery);
  gridDialogFragment.show(getSupportFragmentManager(), "tag");
```
Prima del metodo show oppure prima di inserirlo in un contenitore bisogna chiamare i metodi per configurarlo.
Un parametro obbligatorio da passargli è una lista di oggetti BSDImage.

Si può chiamare anche setLightTheme(boolean) per impostare se usare il tema chiaro o scuro (default true).
Le dimensioni del dialog sono 600x600 ma si possono modificare con setSize(width, height).
setShowTitle(boolean) definisce se si deve visualizzare o meno il titolo (solo per BSDImageSwitcherDialogFragment).
Per BSDImageGridDialog si possono passare il numero di colonne con il metodo setNumColumns(int).



Download
--------

Aggiungere al repository il percorso:
```groovy
repositories {
        jcenter()
        maven {
            url "http://dl.bintray.com/bsdsoftware/bsdsoftware"
        }
    }
```
e nel gradle file la dipendenza:
```groovy
compile 'it.bsdsoftware:bsd-android-gallery:1.8'
```
