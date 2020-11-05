module.exports = function(eleventyConfig) {
    eleventyConfig.addPassthroughCopy('css')
    return {
      passthroughFileCopy: true,
      dir: {
        input: 'content',
        includes: '_includes',
        data: '_data',
        output: 'docs',
      },
    }
  }