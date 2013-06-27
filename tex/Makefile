ENCODING=utf-8
LANG=pt_PT
# nao meter o directorio completo, ie milestone1/relatz.tex esta errado
REPORT_FILE=relatz.tex
REPORT_NAME=relatz
PDF=report

report:
	pdflatex $(REPORT_FILE)
	#for auxfile in $(REPORT_NAME)*.aux;	do\
	#	bibtex `basename $$auxfile .aux` ;\
	#done
	#pdflatex $(REPORT_FILE)
	pdflatex $(REPORT_FILE)
	mv -v $(REPORT_NAME).pdf $(PDF).pdf


# TODO: aspell

clean:
	@rm -v *.{aux,bbl,blg,log,nlo,out,toc,bak}

aspell:
	for i in *.tex ; do\
	 	aspell --encoding=$(ENCODING) --lang=$(LANG) check $$i; \
	done
