$(document).on('ready', function () {
        
        // INITIALIZATION OF TAGIFY
        // =======================================================
        $('.js-tagify').each(function () {
          var tagify = $.HSCore.components.HSTagify.init($(this));
        });

        $('.js-tagify-avatars').each(function () {
          var settings = $(this).attr('data-hs-tagify-options') ? JSON.parse($(this).attr('data-hs-tagify-options')) : {},
            tagifyAvatars = $.HSCore.components.HSTagify.init($(this), {
              templates: {
                tag: function tag(tagData) {
                  try {
                    return "<tag title=\"" + tagData.value + "\" contenteditable=\"false\" spellcheck=\"false\" class=\"tagify__tag " + (tagData["class"] ? tagData["class"] : "") + "\" " + this.getAttributes(tagData) + ">" +
                      "<x title=\"remove tag\" class=\"tagify__tag__removeBtn\"></x>" +
                      "<div class=\"d-flex align-items-center\">" +
                      "" + (tagData.src ? "<img class=\"avatar avatar-xss avatar-circle mr-2\" src=\"" + tagData.src.toLowerCase() + "\">" : "") + "" +
                      "<span>" + tagData.value + "</span>" +
                      "</div>" +
                      "</tag>";
                  } catch (err) {}
                },
                dropdownItem: function dropdownItem(tagData) {
                  try {
                    return "<div " + this.getAttributes(tagData) + " class=\"tagify__dropdown__item " + (tagData["class"] ? tagData["class"] : "") + "\">" +
                      "<img class=\"avatar avatar-xss avatar-circle mr-2\" src=\"" + tagData.src.toLowerCase() + "\">" +
                      "<span>" + tagData.value + "</span>" +
                      "</div>";
                  } catch (err) {}
                }
              }
            }).addTags(settings.whitelist.slice(0, 1));
        });
      });
      