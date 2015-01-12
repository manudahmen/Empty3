for %%i in (*.jar) do set jar=%%i
@echo %jar%

java -cp lib\jai-core-1.1.3.jar;lib\jmf-2.1.1e.jar;lib\MonteMediaCC-0.7.7.jar;lib\jogl-all-main-2.0.2-rc12.jar;%jar% info.emptycanvas.library.gui.NEWMain