build:
	npx @11ty/eleventy --input=./content --output=_site

serve:
	npx @11ty/eleventy --input=./content --output=_site --serve