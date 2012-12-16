require 'puppet'

class Puppeteer
  def initialize
    Puppet.initialize_settings
  end

  def catalog(node_name)
    Puppet::Resource::Catalog.indirection.find(node_name).to_yaml
  end
end