package com.wttch.wcbs.web.jackson;

import com.fasterxml.jackson.databind.Module;

public interface ModuleProvider {
  Module getModule();
}
